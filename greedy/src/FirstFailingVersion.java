public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        return badVersion(n, n/2, isBadVersion);
    }

    public static long badVersion(long n, long diff, IsFailingVersion isBadVersion){
        if(diff/2 == 0){
            diff = 1;
        }
        else {
            diff = diff / 2;
        }

        if(isBadVersion.isFailingVersion(n)){
            if(!isBadVersion.isFailingVersion(n-1)){
                return n;
            }
            return badVersion(n-diff, diff, isBadVersion);
        }
        return badVersion(n+diff, diff, isBadVersion);
    }
}
