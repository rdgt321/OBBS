package DBC;

import Promotion.PromotionPO;
import RMI.ResultMessage;

public interface PromotionDAO {
	public ResultMessage addPromotion(PromotionPO promotionPO);

	public ResultMessage deletePromotion(int promotionID);

	public ResultMessage queryPromotion(int promotionID);

	public ResultMessage updatePromotion(PromotionPO promotionPO);

	public ResultMessage getPromotionList();
}
