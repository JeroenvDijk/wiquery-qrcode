package nl.topicus.wqrcode.js;

import org.apache.wicket.markup.html.resources.CompressedResourceReference;
import org.odlabs.wiquery.core.commons.CoreJavaScriptResourceReference;

/**
 * Script voor JSON-browserobject-support in IE7
 */
public class JsonJavascriptReference extends CompressedResourceReference
{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * Singleton instance.
	 */
	private static JsonJavascriptReference instance = new JsonJavascriptReference();;

	/**
	 * Returns the {@link CoreJavaScriptResourceReference} instance.
	 */
	public static JsonJavascriptReference get()
	{
		return instance;
	}

	/**
	 * Builds a new instance of {@link CoreJavaScriptResourceReference}.
	 */
	private JsonJavascriptReference()
	{
		super(JsonJavascriptReference.class, "json2.pack.js");
	}
}
