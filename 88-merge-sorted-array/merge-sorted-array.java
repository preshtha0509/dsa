class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int [] arr = new int[m+n];
        
        for(int i=0; i<nums1.length; i++){
            arr[i]= nums1[i];
        }
         for(int i=0; i<nums2.length; i++){
            arr[i+m]= nums2[i];
        }
        Arrays.sort(arr);

        for(int i=0;i<arr.length;i++){
            nums1[i] = arr[i];
        }
    }
}