package sorcer.account.provider;

public class StringsException extends Exception {
	public boolean withdrawalSucceeded;

	public StringsException(Exception cause) {
		super(cause);
	}
}
