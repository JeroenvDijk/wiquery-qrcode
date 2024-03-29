package nl.topicus.wqrcode.web.page;

import nl.topicus.wqrcode.QrCodeImageContainer;

import org.apache.wicket.markup.html.WebPage;

public class BasePage extends WebPage
{
	private static final long serialVersionUID = 1L;

	public BasePage()
	{
		add(new QrCodeImageContainer("textqrcode", "http://github.com/JeroenvDijk/wiquery-qrcode", 128));
		add(new QrCodeImageContainer("pageqrcode", BasePage.class, 128));
	}
}
