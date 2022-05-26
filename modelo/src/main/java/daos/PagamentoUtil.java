package daos;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PagamentoUtil {
public static String mostrarDataVencimentoParcela(int numeroParcela){
		
		/*String dataStr=Utils.recebeString("digite a data");
		Date data=Utils.converteStringPraDate(dataStr);
		*/
		Date data=new Date();
		Calendar calendario=Calendar.getInstance();
		calendario.setTime(data);
		int vencimento=numeroParcela*30;
		calendario.add(Calendar.DAY_OF_MONTH,vencimento);
		Date dataSessentaDiasDate=calendario.getTime();
		SimpleDateFormat formatador=new SimpleDateFormat("dd/MM/yyyy");
		String dataDiaDoVencimentoStr=formatador.format(dataSessentaDiasDate);
		return dataDiaDoVencimentoStr;
		// Utils.mostrarMensagemSwing("primeira parcela ap�s 60 dias "+dataSessentaDiasStr);
	}
}
