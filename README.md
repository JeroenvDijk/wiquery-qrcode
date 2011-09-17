# WiQuery QrCodeContainer

QrCodeContainer creates a *QR-code image*. Just add a div and add the content that should be encoded to the QR-code.
Most of the times I think you'd like a web address here.

You can also give the wicket Page instead of text. You'll get the link generated.

The JavaScript is NOT mine

Have a look at: <a href='https://github.com/jeromeetienne/jquery-qrcode' target='_BLANK'>https://github.com/jeromeetienne/jquery-qrcode</a>

What I did is make sure there is a Wicket WebMarkupContainer which now has this jQuery. In the pom maven minify plugin is used to pack these javascript files into one file and also minify it. So in this package you will not see the javascript files which are downloaded from the site named above. 

*<a href='https://oss.sonatype.org/content/groups/staging/nl/topicus/wqrcode/' target='_BLANK'>get jar</a> (not always completely up-to-date)*

## Maven Dependency

Ad this to your dependencies in your pom.xml:

    <dependency>
    	<groupId>nl.topicus</groupId>
    	<artifactId>wqrcode</artifactId>
    	<packaging>jar</packaging>
    	<version>0.1-SNAPSHOT</version>
    </dependency>

Have the following repository added: `http://oss.sonatype.org/content/groups/staging`

## How to Use It

There are three constructors at the moment, which will invert each other:

* id

    the wicket:id
* text or wicket Page

    what should your QR code reader display? This can be just normal text or a web address, your phone will recognise that as a link
* size (optional)

    What size do you want the QR code to be?
* typeNumber (optional)

    This goes from 1 to 10, the bigger the more caracters the QR can contain. But the exact way is a little vague, I'd say just leave it and stay under +/- 120 caracters

    **`public QrCodeImageContainer(String id, String text[[, int size], int typeNumber]) { ... }`**

In the markup you will need an element which can set its outputMarkupId and thus paints its markup; whether it's empty or not. A normal **div** or a **span** is applicable, a wicket:container is not.

So if you have a div:
`<div wicket:id="qr"></div>`
and you have java code 
`add(new QrCodeImageContainer("qr", "http://github.com/JeroenvDijk/wiquery-qrcode"));`
you will see the following image:

<img src='http://www.jeroenvdijk.com/uploads/example.png' />

## That's it!

If you hit bugs, fill issues on github.

Feel free to fork, modify and have fun with it :)