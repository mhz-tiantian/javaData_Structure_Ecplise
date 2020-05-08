package com.mhz.link.single;

import com.mhz.link.List;

/**
 * 测试下, linked 有头部节点的
 * 
 * @author mahaizhen
 *
 * @date 2020年5月8日
 */
public class MainLinkedHead {

	public static void main(String[] args) {
		List<Integer> headLinkList = new SingleLinkedList2<Integer>();
		headLinkList.add(1);
		headLinkList.add(2);
		headLinkList.add(3);
		headLinkList.add(4);
		headLinkList.remove(2);
		headLinkList.toString();

	}
}
