package library.fileException;

public class RepoStorageException extends Exception {
	
	private String message;
	
	public RepoStorageException(String msg) {

		this.message=msg;
	}

	public String toString(String info) {
		return RepoStorageException.class.getName()+"::::::"+this.message;
	}
	
	
}
