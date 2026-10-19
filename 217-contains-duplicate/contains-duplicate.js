/**
 * @param {number[]} nums
 * @return {boolean}
 */
var containsDuplicate = function(nums) {
    let st = new Set(nums);

    return st.size !== nums.length;
};