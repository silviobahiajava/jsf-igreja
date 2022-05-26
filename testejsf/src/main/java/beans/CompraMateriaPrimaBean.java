package beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.testejsf.utils.Utils;
import daos.CompraMateriaPrimaDao;
import daos.EstoqueMateriaPrimaDao;
import daos.LancamentoDao;
import daos.MateriaPrimaDao;
import entidades.CompraMateriaPrima;
import entidades.EstoqueMateriaPrima;
import entidades.Grupo;
import entidades.ItemCompraMateriaPrima;
import entidades.Lancamento;
import entidades.MateriaPrima;

@ManagedBean(name="compramateriaprimaBean")
@ViewScoped
public class CompraMateriaPrimaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private MateriaPrima materiaPrima=new MateriaPrima();
	private MateriaPrimaDao mpDao=new MateriaPrimaDao();
	private EstoqueMateriaPrima estoqueMateriaPrima=new EstoqueMateriaPrima();
	private EstoqueMateriaPrimaDao empDao=new EstoqueMateriaPrimaDao();
	private CompraMateriaPrima cmp=new CompraMateriaPrima();
	private CompraMateriaPrimaDao cmpDao=new CompraMateriaPrimaDao();
	private LancamentoDao lDao=new LancamentoDao();
	private Lancamento lancamento=new Lancamento();
	private ItemCompraMateriaPrima ic;
	private List<ItemCompraMateriaPrima>itensCompra=new ArrayList<ItemCompraMateriaPrima>();
	
	@PostConstruct
	public void init(){
		materiaPrima=new MateriaPrima();
		estoqueMateriaPrima=new EstoqueMateriaPrima();
		cmp=new CompraMateriaPrima();
		itensCompra=new ArrayList<ItemCompraMateriaPrima>();
	}
	
	public void adicionarItemCompra(){
		//pega os dado da materia prima define como item da compra e adiciona na lista de itens da compra
		  itensCompra=new ArrayList<ItemCompraMateriaPrima>();
		     ic=new ItemCompraMateriaPrima();
				String descricao=materiaPrima.getDescricao();
				int quantidadeComprada=materiaPrima.getQuantidadeComprada();
				BigDecimal valorUnitario=materiaPrima.getPrecoUnitario();
				BigDecimal valorTotal=valorUnitario.multiply(new BigDecimal(quantidadeComprada));
				Grupo g=new Grupo();
				g.setCodigo(8L);
				materiaPrima.setGrupo(g);
				materiaPrima.setDescricao(descricao);
				materiaPrima.setQuantidadeComprada((short) quantidadeComprada);
				materiaPrima.setPrecoUnitario(valorUnitario);
				materiaPrima.setPrecoTotal(valorTotal);
		//ic.setCompraMateriaPrima(cmp);
		ic.setMateriaPrima(materiaPrima);
		ic.setPrecoParcial(valorUnitario);
		ic.setQuantidade(materiaPrima.getQuantidadeComprada());
		
		
		
		itensCompra.add(ic);
		Utils.mostrarMensagemJsfSucesso("materia prima adcionada na lista");
	}
	public void comprarMateriaPrimaDaIgreja(){
		cmp=new CompraMateriaPrima();
		mpDao.salvar(materiaPrima);
		Long codMateriaPrima=mpDao.buscaUlitmoCodigo();
		materiaPrima=mpDao.buscar(codMateriaPrima);
		cmp.setDataCompra(new Date());
		cmp.setDescricao(materiaPrima.getDescricao());
		cmp.setQuantidadeComprada(materiaPrima.getQuantidadeComprada());
		cmp.setValorTotalCompra(materiaPrima.getPrecoTotal());
		cmp.setGrupo(materiaPrima.getGrupo());
		
		
		cmpDao.salvarCompraMateriaPrima(cmp,itensCompra);
	   EstoqueMateriaPrima estoqueMateriaPrimaEncontrado=empDao.buscarPorDescricao(materiaPrima.getDescricao());
		
		estoqueMateriaPrima.setQuantidadeMinina(10);
		estoqueMateriaPrima.setGrupo(materiaPrima.getGrupo());
		estoqueMateriaPrima.setDescricao(materiaPrima.getDescricao());
		
		if(estoqueMateriaPrimaEncontrado!=null){
			int quantidadeAtual=estoqueMateriaPrimaEncontrado.getQuantidadeAtual();
			//int quantidadeComprada=estoqueMateriaPrimaEncontrado.getQuantidadeComprada();
			estoqueMateriaPrima.setQuantidadeAtual(quantidadeAtual+materiaPrima.getQuantidadeComprada());
			//estoqueMateriaPrima.setQuantidadeComprada(quantidadeComprada+materiaPrima.getQuantidadeComprada());
			
			empDao.editar(estoqueMateriaPrima);
		}if (estoqueMateriaPrimaEncontrado==null){
			estoqueMateriaPrima.setQuantidadeComprada(materiaPrima.getQuantidadeComprada());
			estoqueMateriaPrima.setQuantidadeAtual(materiaPrima.getQuantidadeComprada());
			empDao.salvar(estoqueMateriaPrima);	
		}
		//BigDecimal despesa=lDao.mostrarDespesaAtualPorGrupo(8L,cmp.getDataCompra());
	//	Lancamento lc=new Lancamento();
		Long codLancamento=lDao.buscaUlitmoCodigo();
		Lancamento lc=lDao.buscar(codLancamento);
		BigDecimal despesa=lc.getDespesa();
		lc.setDespesa(despesa.add(materiaPrima.getPrecoTotal()));
		lc.setGrupo(materiaPrima.getGrupo());
		lDao.editar(lc);
		
		Utils.mostrarMensagemJsfSucesso("compra registrada com sucesso");
		
	}
	public MateriaPrima getMateriaPrima() {
		return materiaPrima;
	}
	public void setMateriaPrima(MateriaPrima materiaPrima) {
		this.materiaPrima = materiaPrima;
	}
	public EstoqueMateriaPrima getEstoqueMateriaPrima() {
		return estoqueMateriaPrima;
	}
	public void setEstoqueMateriaPrima(EstoqueMateriaPrima estoqueMateriaPrima) {
		this.estoqueMateriaPrima = estoqueMateriaPrima;
	}
	public CompraMateriaPrima getCmp() {
		return cmp;
	}
	public void setCmp(CompraMateriaPrima cmp) {
		this.cmp = cmp;
	}
	public Lancamento getLancamento() {
		return lancamento;
	}
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	public ItemCompraMateriaPrima getIc() {
		return ic;
	}
	public void setIc(ItemCompraMateriaPrima ic) {
		this.ic = ic;
	}
	public List<ItemCompraMateriaPrima> getItensCompra() {
		return itensCompra;
	}
	public void setItensCompra(List<ItemCompraMateriaPrima> itensCompra) {
		this.itensCompra = itensCompra;
	}
	
	
	
}
