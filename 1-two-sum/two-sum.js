/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function(nums, target) {
    let mp = new Map();
    let n = nums.length;

    for(let i = 0; i < n; i++){
        let rem = target - nums[i];

        if(mp.has(rem)){
            return [mp.get(rem), i];
        }
        else{
            mp.set(nums[i], i);
        }
    }
};