class Solution {
    class Node {
        char leftChar;
        char rightChar;
        int prefix;
        int suffix;
        int best;
        int length;

        Node(char leftChar, char rightChar, int prefix, int suffix, int best, int length){
            this.leftChar = leftChar;
            this.rightChar = rightChar;
            this.prefix = prefix;
            this.suffix = suffix;
            this.best = best;
            this.length = length;
        }
    }

    class SegmentTree{
        Node[] tree;
        String s;

        SegmentTree(String s){
            this.s = s;
            int n = s.length();
            tree = new Node[4 * n];
            build(1, 0, n - 1);
        }

        void build(int node, int start, int end){
            if(start == end){
                char c = s.charAt(start);
                tree[node] = new Node(c, c, 1, 1, 1, 1);
                return;
            }

            int mid = (start + end) / 2;
            build(2*node, start, mid);
            build(2*node+1, mid+1, end);

            tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
        }

        Node merge(Node left, Node right){
            char leftChar = left.leftChar;
            char rightChar = right.rightChar;
            int prefix = left.prefix;
            int suffix = right.suffix;

            int best = Math.max(left.best, right.best);
            int length = left.length + right.length;

            if(left.rightChar == right.leftChar){
                best = Math.max(best, left.suffix + right.prefix);

                if(left.prefix == left.length){
                    prefix = left.length + right.prefix;
                }

                if(right.suffix == right.length){
                    suffix = right.length + left.suffix;
                }
            }

            return new Node(leftChar, rightChar, prefix, suffix, best, length);
        }

        void update(int node, int start, int end, int index, char value){
            if(start == end){
                tree[node] = new Node(value, value, 1, 1, 1, 1);
                return;
            }

            int mid = (start + end) / 2;

            if(index <= mid){
                update(2 * node, start, mid, index, value);
            } 
            else{
                update(2 * node + 1, mid + 1, end, index, value);
            }

            tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
        }

        int getAnswer(){
            return tree[1].best;
        }
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices){
        int k = queryIndices.length;
        int[] answer = new int[k];
        SegmentTree st = new SegmentTree(s);

        for(int i = 0; i < k; i++){
            st.update(1, 0, s.length() - 1, queryIndices[i], queryCharacters.charAt(i));
            answer[i] = st.getAnswer();
        }

        return answer;
    }
}