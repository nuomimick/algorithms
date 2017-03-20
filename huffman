class node:
    def __init__(self,weight,parent=None,left=None,right=None):
        self.weight = weight
        self.parent = parent
        self.left = left
        self.right = right
        
weights = [7,5,2,4]
nodes = [node(w) for w in weights]
nodes = sorted(nodes,key=lambda n:n.weight)
while nodes:
    if len(nodes) == 1:
        parent_node = nodes[0]
        break
    node_one = nodes[0]
    node_two = nodes[1]
    del nodes[1]
    del nodes[0]
    node_new = node(node_one.weight+node_two.weight,None,node_one,node_two)
    node_one.parent = node_new
    node_two.parent = node_new
    for i in range(len(nodes)):
        if node_new.weight < nodes[i].weight:
            nodes.insert(i,node_new)
            break
    if not nodes or nodes[i].weight < node_new.weight:
        nodes.insert(i+1,node_new)
     
def printTree(node,flag):
    if node == None:
        return
    print node.weight,flag
    printTree(node.left,0)
    printTree(node.right,1)
    
printTree(parent_node,None)
