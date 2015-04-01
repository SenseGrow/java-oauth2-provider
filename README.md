<h1> Oauth2 Provider Library for Java</h1>
<p>This library provides a toolkit for adding OAuth2 provider capabilities to a Java web app. The library has been designed to be usable with play framework, servlet based web frameworks. The library is currently in the beta stage.</p> 

<h1>Supported Grant Types</h1>
The library has been based on draft-31 of the standard. The library will support the following grant types:
<ul>
<li>Authorization Code Grant</li>
<li>Resource Owner Password Credentials Grant</li>
<li>Client Credentials Grant</li>
</ul>

<h1>Supported Token Types</h1>
<ul>
<li>Bearer Token</li>
</ul>

<h1>Installation</h1>
TODO

<h1>Usage with Servlet</h1>
TODO

<h1>Usage with Play Framework</h1>
First you need to include this library in your project. <br />
Then you need to implement all model in your project.  <br />
Then you need to implement com.sensegrow.oauth2provider.request.OAuthRequest in your project. <br />
Then you need to create an OauthController which contains two methods <br />
1. auth(String client_id, String client_secret,String redirect_uri, String response_type, String email,	Long user_id) : for authorization_token <br />

2.accessToken() : for access_token. 


<h1>Examples</h1>
TODO

<h1>Contributing</h1>

We encourage you to contribute to the java Oath2 library!

<h1>License</h1>
<p>Oauth2 Java Provider is released under the <a href="http://www.opensource.org/licenses/MIT">MIT License</a>.</p>
