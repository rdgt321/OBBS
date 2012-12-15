package Book;

import RMI.addBook;
import RMI.addDirectory;
import RMI.deleteBook;
import RMI.deleteDirectory;
import RMI.getAllBooks;
import RMI.getAllDirectories;
import RMI.getSelectedBook;
import RMI.getSelectedDirectory;
import RMI.getTopBooks;
import RMI.getTopDirectories;
import RMI.modifyBook;
import RMI.modifyDirectory;
import RMI.searchBook;

public interface BookService extends addBook, addDirectory, deleteBook,
		deleteDirectory, modifyBook, modifyDirectory, getSelectedBook,
		getSelectedDirectory, getAllBooks, getAllDirectories, getTopBooks,
		getTopDirectories, searchBook {
}
