public interface QSTestInterface
{
	/**
	 * Returns true if the given QSInterface object correctly sorts.
	 * Correctness is defined by the lab requirements and the QSInterface.
	 * 
	 * @param test
	 *            an implementation of the QSInterface
	 * @return true if [test] correctly sorts; false otherwise
	 */
	public boolean testSort(QSInterface test);

	/**
	 * Returns true if the given QSInterface object correctly selects a pivot.
	 * Correctness is defined by the lab requirements and the QSInterface.
	 * 
	 * @param test
	 *            an implementation of the QSInterface
	 * @return true if [test] correctly selects a pivot; false otherwise
	 */
	public boolean testMedianOfThree(QSInterface test);

	/**
	 * Returns true if the given QSInterface object correctly partitions.
	 * Correctness is defined by the lab requirements and the QSInterface.
	 * 
	 * @param test
	 *            an implementation of the QSInterface
	 * @return true if [test] correctly partitions; false otherwise
	 */
	public boolean testPartition(QSInterface test);

	/**
	 * Returns true if the given QSInterface object correctly swaps elements.
	 * Correctness is defined by the lab requirements and the QSInterface.
	 * 
	 * @param test
	 *            an implementation of the QSInterface
	 * @return true if [test] correctly swaps elements; false otherwise
	 */
	public boolean testSwap(QSInterface test);
}
