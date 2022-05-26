package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="renderedBean")
@ViewScoped
public class RenderedBean {
private String tipoPessoa;

public String getTipoPessoa() {
	return tipoPessoa;
}

public void setTipoPessoa(String tipoPessoa) {
	this.tipoPessoa = tipoPessoa;
}

}
