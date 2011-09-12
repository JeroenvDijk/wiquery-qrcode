package nl.topicus.wqrcode.js;

import org.odlabs.wiquery.core.commons.CoreJavaScriptResourceReference;
import org.odlabs.wiquery.core.commons.WiQueryJavaScriptResourceReference;

public class QrCodeJavascriptReference extends WiQueryJavaScriptResourceReference
{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * Singleton instance.
	 */
	private static QrCodeJavascriptReference instance = new QrCodeJavascriptReference();;

	/**
	 * Returns the {@link CoreJavaScriptResourceReference} instance.
	 */
	public static QrCodeJavascriptReference get()
	{
		return instance;
	}

	/**
	 * Builds a new instance of {@link CoreJavaScriptResourceReference}.
	 */
	private QrCodeJavascriptReference()
	{
		super(QrCodeJavascriptReference.class, "jquery.qrcode.pack.js");
	}
}
