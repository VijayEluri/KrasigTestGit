package test1;
/**
 * fastbindclient.java
 * 
 * Sample JNDI application to use LDAP_SERVER_FAST_BIND connection control
 * 
 * This is just a test harness to invoke the ldapfastbind methods
 * 111
 * 222
 */
 
class fastbindclient{
	public static void main (String[] args)	{
	//Could also use ldaps over port 636 to protect the communication to the 
	//Active Directory domain controller. Would also need to add 
	//env.put(Context.SECURITY_PROTOCOL,"ssl") to the "server" code
	String ldapurl = "ldap://lirex.com:389";
	boolean IsAuthenticated = false;
	ldapfastbind ctx = new ldapfastbind(ldapurl);
	IsAuthenticated = ctx.Authenticate("indexbg\\krasi","krasig");
	IsAuthenticated = ctx.Authenticate("indexbg\\vassil","cybernet");
	IsAuthenticated = ctx.Authenticate("indexbg\\testuser2","TestUser2");
	IsAuthenticated = ctx.Authenticate("ANTIPODES\\charlesd","BadPassword");
	IsAuthenticated = ctx.Authenticate("ANTIPODES\\isaacn","GoodPassword");
	IsAuthenticated = ctx.Authenticate("ANTIPODES\\isaacn","BadPassword");
	ctx.finito();
 
	}
}
