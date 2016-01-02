
public class QSTestFactory
{
	/**
	 * Creates and returns an object whose class implements QSTestInterface.
	 * This should be an object of the class you have created.
	 * 
	 * Example: If you made a class called "QSTest", you might say "return new QSTest()"
	 * 
	 * @return a new object whose class implements QSTestInterface
	 */
	public static QSTestInterface createQSTest()
	{
		return new QSTest();
	}
}