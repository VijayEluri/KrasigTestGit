package test1;
/* ldapfastbind.java
* 
* Sample JNDI application to use Active Directory LDAP_SERVER_FAST_BIND connection control
* 
*/

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

class FastBindConnectionControl implements Control {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6816941722609631483L;
	public byte[] getEncodedValue() {
       	return null;
	}
 	public String getID() {
		return "1.2.840.113556.1.4.1781";
	}
	public boolean isCritical() {
		return true;
	}
}

public class ldapfastbind {
	public Hashtable<String,String> env = null;
	public LdapContext ctx = null;
	public Control[] connCtls = null;

	
	public ldapfastbind(String ldapurl) {
		env = new Hashtable<String,String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.SECURITY_AUTHENTICATION,"simple");
		env.put(Context.PROVIDER_URL,ldapurl);

		connCtls = new Control[] {new FastBindConnectionControl()};

		//first time we initialize the context, no credentials are supplied
		//therefore it is an anonymous bind.		

		try {
			ctx = new InitialLdapContext(env,connCtls);

		}
		catch (NamingException e) {
			System.out.println("Naming exception " + e);
		}
	}
	public boolean Authenticate(String username, String password) {
		try {
			ctx.addToEnvironment(Context.SECURITY_PRINCIPAL,username);
			ctx.addToEnvironment(Context.SECURITY_CREDENTIALS,password);
			ctx.reconnect(connCtls);
			System.out.println(username + " is authenticated");
			return true;
		}

		catch (AuthenticationException e) {
			System.out.println(username + " is not authenticated");
			return false;
		}
		catch (NamingException e) {
			System.out.println(username + " is not authenticated");
			return false;
		}
	}
	public void finito() {
		try {
			ctx.close();
			System.out.println("Context is closed");
		}
		catch (NamingException e) {
			System.out.println("Context close failure " + e);
		}
	}
}