package utils;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import beans.AutenticacaoBean;
import entidades.Usuario;

public class PermissaoListener implements PhaseListener{
	private static final long serialVersionUID = 1L;

	public void afterPhase(PhaseEvent event) {
		String paginaAtual = Faces.getViewId();
		boolean ehPaginaAtual = paginaAtual.contains("autenticacao.xhtml");
		if(!ehPaginaAtual){
		AutenticacaoBean autenticacaoBean=Faces.getSessionAttribute("autenticacaoBean");
			if(autenticacaoBean==null){
				Faces.navigate("./public/autenticacao.xhtml");
				return;
			}
			Usuario usuarioLogado = autenticacaoBean.getUsuarioLogado();
			if(usuarioLogado==null){
				Faces.navigate("./public/autenticacao.xhtml");
				return;
			}
			
		}
		}

	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		
	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
