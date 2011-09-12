package nl.topicus.wqrcode;

import nl.topicus.wqrcode.js.JsonJavascriptReference;
import nl.topicus.wqrcode.js.QrCodeJavascriptReference;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.WicketTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.core.CoreUIJavaScriptResourceReference;
import org.odlabs.wiquery.ui.widget.WidgetJavascriptResourceReference;

/**
 * This creates a QR-code image. Just add a div and add the content that should be encoded to the QR-code. Most of the
 * times I think you'd like a web address here.
 * 
 * TODO: something with the page and create the link automatically
 * 
 * @author Jeroen v. Dijk
 */
@WiQueryUIPlugin
public class QrCodeImageContainer extends WebMarkupContainer implements IWiQueryPlugin
{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** all parameters for the QR code are stored here */
	private Options options = new Options();

	/**
	 * Constructor
	 * 
	 * @param id
	 *            the wicket:id
	 * @param text
	 *            what should your QR code reader read?
	 */
	public QrCodeImageContainer(String id, String text)
	{
		this(id, text, 64);
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            the wicket:id
	 * @param text
	 *            what should your QR code reader read?
	 * @param size
	 *            What size do you want the QR code to be?
	 */
	public QrCodeImageContainer(String id, String text, int size)
	{
		this(id, text, size, 4);
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            the wicket:id
	 * @param text
	 *            what should your QR code reader read?
	 * @param size
	 *            What size do you want the QR code to be?
	 * @param typeNumber
	 *            This goes from 1 to 10, the bigger the more caracters the QR can contain. But the exact way is a
	 *            little vague, I'd say just leave it and stay under +/- 120 caracters
	 */
	public QrCodeImageContainer(String id, String text, int size, int typeNumber)
	{
		super(id);

		setOutputMarkupPlaceholderTag(true);
		setText(text);
		setSize(size);
		setTypeNumber(checkTypeNumber(text.length(), typeNumber));
	}

	/*
	 * to be quite honest I don't really know the calculation, but this fixes errors when you try to encode too much
	 * data and a too small type number. I also figured out that the javascript allowes a typeNumber between 1 and 10,
	 * but more I don't know
	 * 
	 * @param length
	 * 
	 * @param typeNumber
	 * 
	 * @return typeNumber
	 */
	private int checkTypeNumber(int length, int typeNumber)
	{
		if (typeNumber < 1)
		{
			typeNumber = 1;
		}
		while (typeNumber < 10 && !typeHighEnough(typeNumber, length * 8 + 12))
		{
			typeNumber += 1;
		}
		return typeNumber;
	}

	private boolean typeHighEnough(int typeNumber, int lengthCalculation)
	{
		switch (typeNumber)
		{
		case 1:
			return lengthCalculation < 72;
		case 2:
			return lengthCalculation < 128;
		case 3:
			return lengthCalculation < 208;
		case 4:
			return lengthCalculation < 288;
		case 5:
			return lengthCalculation < 368;
		case 6:
			return lengthCalculation < 480;
		case 7:
			return lengthCalculation < 528;
		case 8:
			return lengthCalculation < 688;
		case 9:
			return lengthCalculation < 800;
		case 10: // unused, because I stop the while loop at 10, but now you know the max the JS can handle
			return lengthCalculation < 976;
		default:
		}
		return false;
	}

	public String getOnClickJavascript()
	{
		return statement().getStatement().toString() + "; return false;";
	}

	@Override
	public void contribute(WiQueryResourceManager wiQueryResourceManager)
	{
		wiQueryResourceManager.addJavaScriptResource(CoreUIJavaScriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(WidgetJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(JsonJavascriptReference.get());
		wiQueryResourceManager.addJavaScriptResource(QrCodeJavascriptReference.get());
	}

	@Override
	public JsStatement statement()
	{
		return new JsStatement().$(this).chain("qrcode", options.getJavaScriptOptions());
	}

	public String getText()
	{
		return StringEscapeUtils.unescapeJavaScript(options.get("text"));
	}

	public QrCodeImageContainer setText(String text)
	{
		options.put("text", "'" + StringEscapeUtils.escapeJavaScript(text) + "'");
		return this;
	}

	public QrCodeImageContainer setSize(int size)
	{
		options.put("width", size);
		options.put("height", size);
		return this;
	}

	public QrCodeImageContainer setTypeNumber(int typeNumber)
	{
		options.put("typeNumber", typeNumber);
		return this;
	}

	@Override
	protected void onComponentTag(ComponentTag tag)
	{
		if (getRenderBodyOnly() || tag instanceof WicketTag)
		{
			throw new IllegalStateException(
				"For QR Code the tag has to appear in the markup and the markupId has to be set.");
		}
		super.onComponentTag(tag);
	}

}