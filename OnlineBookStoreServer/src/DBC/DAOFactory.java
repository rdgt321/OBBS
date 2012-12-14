package DBC;

public class DAOFactory {
	private static DAOFactory daoFactory;
	private static BookDAO bookDAO;
	private static CartItemDAO cartItemDAO;
	private static CollectDAO collectDAO;
	private static CouponsDAO couponsDAO;
	private static DirectoryDAO directoryDAO;
	private static EquivalentBondDAO equivalentBondDAO;
	private static MemberDAO memberDAO;
	private static OrderDAO orderDAO;
	private static OrderItemDAO orderItemDAO;
	private static PromotionDAO promotionDAO;
	private static UserDAO userDAO;
	private static MessageDAO messageDAO;

	private DAOFactory() {
		super();
	};

	public static synchronized DAOFactory getInstance() {
		if (daoFactory == null) {
			synchronized (DAOFactory.class) {
				if (daoFactory == null) {
					daoFactory = new DAOFactory();
				}
			}
		}
		return daoFactory;
	}

	public static synchronized BookDAO getBookDAO() {
		if (bookDAO == null) {
			synchronized (DAOFactory.class) {
				if (bookDAO == null) {
					bookDAO = new BookDAOImpl();
				}
			}
		}
		return bookDAO;
	}

	public static synchronized CartItemDAO getCartItemDAO() {
		if (cartItemDAO == null) {
			synchronized (DAOFactory.class) {
				if (cartItemDAO == null) {
					cartItemDAO = new CartItemDAOImpl();
				}
			}
		}
		return cartItemDAO;
	}

	public static synchronized CollectDAO getCollectDAO() {
		if (collectDAO == null) {
			synchronized (DAOFactory.class) {
				if (collectDAO == null) {
					collectDAO = new CollectDAOImpl();
				}
			}
		}
		return collectDAO;
	}

	public static synchronized CouponsDAO getCouponsDAO() {
		if (couponsDAO == null) {
			synchronized (DAOFactory.class) {
				if (couponsDAO == null) {
					couponsDAO = new CouponsDAOImpl();
				}
			}
		}
		return couponsDAO;
	}

	public static synchronized DirectoryDAO getDirectoryDAO() {
		if (directoryDAO == null) {
			synchronized (DAOFactory.class) {
				if (directoryDAO == null) {
					directoryDAO = new DirectoryDAOImpl();
				}
			}
		}
		return directoryDAO;
	}

	public static synchronized EquivalentBondDAO getEquivalentBondDAO() {
		if (equivalentBondDAO == null) {
			synchronized (DAOFactory.class) {
				if (equivalentBondDAO == null) {
					equivalentBondDAO = new EquivalentBondDAOImpl();
				}
			}
		}
		return equivalentBondDAO;
	}

	public static synchronized MemberDAO getMemberDAO() {
		if (memberDAO == null) {
			synchronized (DAOFactory.class) {
				if (memberDAO == null) {
					memberDAO = new MemberDAOImpl();
				}
			}
		}
		return memberDAO;
	}

	public static synchronized OrderDAO getOrderDAO() {
		if (orderDAO == null) {
			synchronized (DAOFactory.class) {
				if (orderDAO == null) {
					orderDAO = new OrderDAOImpl();
				}
			}
		}
		return orderDAO;
	}

	public static synchronized OrderItemDAO getOrderItemDAO() {
		if (orderItemDAO == null) {
			synchronized (DAOFactory.class) {
				if (orderItemDAO == null) {
					orderItemDAO = new OrderItemDAOImpl();
				}
			}
		}
		return orderItemDAO;
	}

	public static synchronized PromotionDAO getPromotionDAO() {
		if (promotionDAO == null) {
			synchronized (DAOFactory.class) {
				if (promotionDAO == null) {
					promotionDAO = new PromotionDAOImpl();
				}
			}
		}
		return promotionDAO;
	}

	public static synchronized UserDAO getUserDAO() {
		if (userDAO == null) {
			synchronized (DAOFactory.class) {
				if (userDAO == null) {
					userDAO = new UserDAOImpl();
				}
			}
		}
		return userDAO;
	}
	
	public static synchronized MessageDAO getMessageDAO() {
		if (messageDAO == null) {
			synchronized (DAOFactory.class) {
				if (messageDAO == null) {
					messageDAO = new MessageDAOImpl();
				}
			}
		}
		return messageDAO;
	}

}
