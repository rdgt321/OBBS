package Promotion;

import RMI.addPromption;
import RMI.deletePromotion;
import RMI.getPromotionList;
import RMI.triggerPromotion;

public interface PromotionService extends addPromption, deletePromotion,
		triggerPromotion ,getPromotionList{

}
