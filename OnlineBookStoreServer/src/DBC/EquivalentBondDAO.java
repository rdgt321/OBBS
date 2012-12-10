package DBC;

import Promotion.EquivalentBondPO;
import RMI.ResultMessage;

public interface EquivalentBondDAO {
	public ResultMessage addEquivalentBond(EquivalentBondPO equivalentBondPO);

	public ResultMessage queryEquivalentBond(int equivalentBondID);

	public ResultMessage deleteEquivalentBond(int equivalentBondID);

	public ResultMessage updateEquivalentBond(EquivalentBondPO equivalentBondPO);

	public ResultMessage getEquivalentBond(int memberID);

}
