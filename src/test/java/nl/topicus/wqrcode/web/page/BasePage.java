package nl.topicus.wqrcode.web.page;

import nl.topicus.wqrcode.QrCodeImageContainer;

import org.apache.wicket.markup.html.WebPage;

public class BasePage extends WebPage
{
	private static final long serialVersionUID = 1L;

	public BasePage()
	{
		add(new QrCodeImageContainer("qrcode", "This is a QR test code", 128));
	}
}
