package cn.edu.util;

import cn.edu.entity.Menu;

import java.util.ArrayList;
import java.util.List;


public class TreeNodeUtil {
	public static List<TreeNode> tree(List<TreeNode>nodes,String id) {
		//递归转化为树形
		 List<TreeNode> treeNodes=new ArrayList<TreeNode>();
	        for(TreeNode treeNode : nodes) {
	            TreeNode node=new TreeNode();
	            node.setId(treeNode.getId());
	            node.setName(treeNode.getName());
	            node.setIsHeader(treeNode.getIsHeader());
	            node.setOrder(treeNode.getOrder());
	            node.setUrl(treeNode.getUrl());
	            node.setIcon(treeNode.getIcon());
	            node.setParentId(treeNode.getParentId());
	            if(id.equals(treeNode.getParentId())){
	                node.setChildMenus(tree(nodes, node.getId()));
	                treeNodes.add(node);
	            }
	             
	        }
	        return treeNodes;
	}	
	

	//转化为TreeNode节点
	public static TreeNode toNode(Menu menu){
		TreeNode node=new TreeNode();
		node.setId(menu.getId().toString());
		node.setName(menu.getName()==null?"":menu.getName());
		node.setParentId(menu.getParentid()==null?"0":menu.getParentid().toString());
		node.setOrder(menu.getOrder()==null?"":menu.getOrder());
		node.setIsHeader(menu.getIsheader()==null?"":menu.getIsheader());
		node.setUrl(menu.getUrl()==null?"":menu.getUrl());
		node.setIcon(menu.getIcon()==null?"":menu.getIcon());
		return node;
	}
	public static List<TreeNode>toListNode(List<Menu>menus){
		List<TreeNode>nodes=new ArrayList<TreeNode>();
		for(Menu menu:menus){
			nodes.add(toNode(menu));
		}
		return nodes;
	}
}
