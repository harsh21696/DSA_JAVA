class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for(int[] seat : reservedSeats){
            map.putIfAbsent(seat[0], new HashSet<>());
            map.get(seat[0]).add(seat[1]);
        }

        int count = (n - map.size()) * 2;

        for(int row : map.keySet()){
            Set<Integer> booked = map.get(row);

            boolean left = isAvailable(booked, 2, 5);
            boolean middle = isAvailable(booked, 4, 7);
            boolean right = isAvailable(booked, 6, 9);

            if(left && right){
                count += 2;
            } 
            else if(left || middle || right){
                count += 1;
            }
        }

        return count;
    }

    private boolean isAvailable(Set<Integer> booked, int start, int end){
        for(int i = start; i <= end; i++){
            if(booked.contains(i)){
                return false;
            }
        }
        
        return true;
    }
}