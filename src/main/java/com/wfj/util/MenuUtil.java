package com.wfj.util;

import com.wfj.entity.WechatMenu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 把一个list集合,里面的bean含有 parentId 转为树形式
 *
 */
public class MenuUtil {
	
	
	/**
	 * 根据父节点的ID获取所有子节点
	 * @param list 分类表
	 * @param praentId 传入的父节点ID
	 * @return String
	 */
	public List<WechatMenu> getChildTreeObjects(List<WechatMenu> list,int praentId) {
		List<WechatMenu> returnList = new ArrayList<WechatMenu>();
		for (Iterator<WechatMenu> iterator = list.iterator(); iterator.hasNext();) {
			WechatMenu t = (WechatMenu) iterator.next();
			// 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
			if (t.getParentSid().equals(praentId)) {
				recursionFn(list, t);
				returnList.add(t);
			}
		}
		return returnList;
	}
	
	/**
	 * 递归列表
	 * @author lanyuan
	 * Email: mmm333zzz520@163.com
	 * @date 2013-12-4 下午7:27:30
	 * @param list
	 * @param t
	 */
	private void  recursionFn(List<WechatMenu> list, WechatMenu t) {
		List<WechatMenu> childList = getChildList(list, t);// 得到子节点列表
		t.setChildren(childList);
		for (WechatMenu tChild : childList) {
			if (hasChild(list, tChild)) {// 判断是否有子节点
				//returnList.add(TreeObject);
				Iterator<WechatMenu> it = childList.iterator();
				while (it.hasNext()) {
					WechatMenu n = (WechatMenu) it.next();
					recursionFn(list, n);
				}
			}
		}
	}
	
	// 得到子节点列表
	private List<WechatMenu> getChildList(List<WechatMenu> list, WechatMenu t) {
		
		List<WechatMenu> tlist = new ArrayList<WechatMenu>();
		Iterator<WechatMenu> it = list.iterator();
		while (it.hasNext()) {
			WechatMenu n = (WechatMenu) it.next();
			if (n.getParentSid().equals(t.getSid().toString())) {
				tlist.add(n);
			}
		}
		return tlist;
	} 
	List<WechatMenu> returnList = new ArrayList<WechatMenu>();
	/**
     * 根据父节点的ID获取所有子节点
     * @param list 分类表
     * @param typeId 传入的父节点ID
     * @param prefix 子节点前缀
     */
    public List<WechatMenu> getChildTreeObjects(List<WechatMenu> list, String typeId,String prefix){
        if(list == null) return null;
        for (Iterator<WechatMenu> iterator = list.iterator(); iterator.hasNext();) {
			WechatMenu node = (WechatMenu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getParentSid().equals(typeId)) {
                recursionFn(list, node,prefix);
            }
            // 二、遍历所有的父节点下的所有子节点
            /*if (node.getParentId()==0) {
                recursionFn(list, node);
            }*/
        }
        return returnList;
    }
     
    private void recursionFn(List<WechatMenu> list, WechatMenu node,String p) {
        List<WechatMenu> childList = getChildList(list, node);// 得到子节点列表
        if (hasChild(list, node)) {// 判断是否有子节点
            returnList.add(node);
            Iterator<WechatMenu> it = childList.iterator();
            while (it.hasNext()) {
				WechatMenu n = (WechatMenu) it.next();
                n.setName(p+n.getName());
                recursionFn(list, n,p+p);
            }
        } else {
            returnList.add(node);
        }
    }

	// 判断是否有子节点
	private boolean hasChild(List<WechatMenu> list, WechatMenu t) {
		return getChildList(list, t).size() > 0 ? true : false;
	}
	
	// 本地模拟数据测试
	public void main(String[] args) {
		/*long start = System.currentTimeMillis();
		List<TreeObject> TreeObjectList = new ArrayList<TreeObject>();
		
		TreeObjectUtil mt = new TreeObjectUtil();
		List<TreeObject> ns=mt.getChildTreeObjects(TreeObjectList,0);
		for (TreeObject m : ns) {
			System.out.println(m.getName());
			System.out.println(m.getChildren());
		}
		long end = System.currentTimeMillis();
		System.out.println("用时:" + (end - start) + "ms");*/
	}
	
}
