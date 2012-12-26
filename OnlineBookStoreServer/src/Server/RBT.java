package Server;

import java.util.ArrayList;

import RMI.UserAgent;

public class RBT {
	private static final int RED = 0;
	private static final int BLACK = 1;

	private static Node nil = new Node(BLACK);
	private int size = 0;
	private Node root = nil;

	public int getSize() {
		return size;
	}

	public Node search(UserAgent userAgent) {
		return search(userAgent.getKey(), root);
	}

	public ArrayList<UserAgent> toArray() {
		ArrayList<UserAgent> array = new ArrayList<UserAgent>();
		fill(array, root);
		return array;
	}

	public void fill(ArrayList<UserAgent> array, Node node) {
		if (node == nil) {
			return;
		} else {
			fill(array, node.left);
			array.add(node.userAgent);
			fill(array, node.right);
		}
	}

	public Node search(int key, Node node) {
		if (node == null || (node == nil && node != root)) {
			return nil;
		}
		if (key == node.key) {
			return node;
		} else {
			if (key > node.key) {
				node = node.right;
			} else if (key < node.key) {
				node = node.left;
			}
			return search(key, node);
		}
	}

	// 插入结点
	public boolean insert(UserAgent userAgent) {
		Node newNode = new Node(userAgent);
		int key = newNode.key;
		newNode.color = RED; // 新插入的结点全部先赋值为红色
		Node parent = nil;
		Node current = root;
		while (current != nil) {
			parent = current;
			if (key < current.key)
				current = current.left;
			else if (key > current.key)
				current = current.right;
			else
				return false;
		}
		if (parent == nil) {
			root = newNode;
			root.parent = nil;
		} else {
			newNode.parent = parent;
			if (key < parent.key) {
				parent.left = newNode;
			} else {
				parent.right = newNode;
			}
		}
		newNode.left = nil;
		newNode.right = nil;
		// 恢复红黑树性质
		RB_insert_fixup(newNode);
		size++;
		return true;
	}

	// 修复插入操作时破坏的红黑树性质
	public void RB_insert_fixup(Node z) {
		while (z.parent.color == RED) {
			if (z.parent == z.parent.parent.left) {
				Node y = z.parent.parent.right;
				// case1:z的叔叔y是红色的
				if (y.color == RED) {
					z.parent.color = BLACK;
					y.parent.color = RED;
					y.color = BLACK;
					z = z.parent.parent;
				} else {
					// case2:z的叔叔y是黑色的，且z是右孩子
					if (z == z.parent.right) {
						z = z.parent;
						left_rotate(z);
					}
					// case3:z的叔叔y是黑色的，且z是左孩子
					z.parent.color = BLACK;
					z.parent.parent.color = RED;
					right_rotate(z.parent.parent);
				}
			} else {// 左右的情况是对称的
				Node y = z.parent.parent.left;
				if (y.color == RED) {
					z.parent.color = BLACK;
					y.parent.color = RED;
					y.color = BLACK;
					z = z.parent.parent;
				} else {
					if (z == z.parent.left) {
						z = z.parent;
						right_rotate(z);
					}
					z.parent.color = BLACK;
					z.parent.parent.color = RED;
					left_rotate(z.parent.parent);
				}
			}
		}
		root.color = BLACK;
	}

	/*
	 * 删除结点操作总是在只有一边有子女的结点或者叶子结点上进行的, 绝不会在一个有二个子女的结点上进行删除操作,
	 * successor函数只有在节点有2个子女的时候被调用, 这个时候，该函数一定是沿节点的右子树向下进行的, 最终会找到一个只有一个孩子的结点
	 */
	public void delete(UserAgent userAgent) {
		int key = userAgent.getKey();
		// 定位到即将被删除的结点
		Node deleteNode = search(key, root);
		// 实际被删的结点
		Node delete;
		// 被删结点的唯一的孩子
		Node x;
		// 被删结点没有子女或只有一个子女时,删除的就是该结点
		if (deleteNode.left == nil || deleteNode.right == nil)
			delete = deleteNode;
		else
			// 被删结点有两个子女时,删除的是该结点的后继,然后将后继的值赋给该结点
			delete = successor(deleteNode);
		if (delete.left != nil)
			x = delete.left;
		else
			x = delete.right;
		x.parent = delete.parent;
		if (delete.parent == nil) {
			root = x;
		} else {
			if (delete == delete.parent.left) {
				delete.parent.left = x;
			} else {
				delete.parent.right = x;
			}
		}
		// 注意替换key
		if (delete != deleteNode) {
			deleteNode.key = delete.key;
		}
		// 恢复红黑树性质
		if (delete.color == BLACK) {
			RB_delete_fixup(x);
		}
		size--;
	}

	// 修复删除操作时破坏的红黑树性质
	public void RB_delete_fixup(Node x) {
		while (x != root && x.color == BLACK) {
			/*
			 * 为保持黑高度不变,把x想象成为有两重黑色,但这显然不符合红黑性质,所以调整方向有两个：
			 * 1.将x的黑色去掉一个,这就需要将x的兄弟节点的黑高度减一,即case2
			 * 2.通过旋转使x的位置下降,将x的多余的黑色赋给其父结点,即case4
			 */
			if (x == x.parent.left) {
				Node w = x.parent.right;
				// case1:x的兄弟w是红色
				if (w.color == RED) {
					w.color = BLACK;
					w.parent.color = RED;
					left_rotate(x.parent);
					w = x.parent.right;
				}
				// case2x:的兄弟w是黑色,且w的两个孩子是黑色
				if (w.left.color == BLACK && w.right.color == BLACK) {
					w.color = RED;
					x = x.parent;
				} else {
					// case3:x的兄弟w是黑色,w的左孩子是红色，右孩子是黑色
					if (w.right.color == BLACK) {
						w.left.color = RED;
						w.color = RED;
						right_rotate(w);
						w = x.parent.right;
					}
					// case4:x的兄弟w是黑色,w的右孩子是红色
					w.color = x.parent.color;
					x.parent.color = BLACK;
					w.right.color = BLACK;
					left_rotate(x.parent);
					x = root;
				}
			} else {
				Node w = x.parent.left;
				if (w.color == RED) {
					w.color = BLACK;
					w.parent.color = RED;
					right_rotate(x.parent);
					w = x.parent.right;
				}
				if (w.left.color == BLACK && w.right.color == BLACK) {
					w.color = RED;
					x = x.parent;
				} else {
					if (w.left.color == BLACK) {
						w.right.color = RED;
						w.color = RED;
						left_rotate(w);
						w = x.parent.left;
					}
					w.color = x.parent.color;
					x.parent.color = BLACK;
					w.left.color = BLACK;
					right_rotate(x.parent);
					x = root;
				}
			}
		}
		x.color = BLACK;
	}

	// 左旋
	public void left_rotate(Node node) {
		Node tn = node.right;
		node.right = tn.left;
		if (tn.left != nil)
			tn.left.parent = node;
		// 要时刻注意旋转时根结点的变换
		tn.parent = node.parent;
		if (node.parent == nil)
			root = tn;
		else if (node == node.parent.left)
			node.parent.left = tn;
		else
			node.parent.right = tn;
		tn.left = node;
		node.parent = tn;
	}

	// 右旋
	public void right_rotate(Node node) {
		Node tn = node.left;
		node.left = tn.right;
		if (tn.right != nil)
			tn.right.parent = node;
		tn.parent = node.parent;
		if (node.parent == nil)
			root = tn;
		else if (node == node.parent.left)
			node.parent.left = tn;
		else
			node.parent.right = tn;
		tn.right = node;
		node.parent = tn;
	}

	// 最小值(最大值将left换为right即可)
	public Node min(Node node) {
		while (node.left != nil) {
			node = node.left;
		}
		return node;
	}

	// 后继结点
	public Node successor(Node node) {
		if (node.right != nil) {
			return min(node.right);
		} else {
			Node parent = node.parent;
			while (parent != nil && node == parent.right) {
				node = parent;
				parent = node.parent;
			}
			return parent;
		}
	}

}

class Node {
	UserAgent userAgent;
	int key;
	int color;
	Node left;
	Node right;
	Node parent;

	public Node(UserAgent userAgent) {
		this.userAgent = userAgent;
		this.key = userAgent.getKey();
	}

	public Node(int color) {
		this.color = color;
		this.key = -1;// 哨兵作为叶子,值要最小
		this.userAgent = null;
	}
}