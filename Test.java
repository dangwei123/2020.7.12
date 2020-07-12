给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。
在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。

只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 

 

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/satisfiability-of-equality-equations
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public boolean equationsPossible(String[] equations) {
        UnionSet set=new UnionSet(26);
        for(String s:equations){
            if(s.charAt(1)=='='){
                set.union(s.charAt(0)-'a',s.charAt(3)-'a');
            }
        }
        for(String s:equations){
            if(s.charAt(1)=='!'){
                if(set.find(s.charAt(0)-'a')==set.find(s.charAt(3)-'a')){
                    return false;
                }
            }
        }
        return true;
    }
}

class UnionSet{
    int[] arr;
    int n;
    public UnionSet(int n){
        this.n=n;
        arr=new int[n];
        Arrays.fill(arr,-1);
    }

    public int find(int num){
        while(arr[num]>=0){
            num=arr[num];
        }
        return num;
    }

    public boolean union(int a,int b){
        int rootA=find(a);
        int rootB=find(b);

        if(rootA==rootB){
            return false;
        }

        arr[rootB]+=arr[rootA];
        arr[rootA]=rootB;
        return true;
    }

    public int count(){
        int count=0;
        for(int i:arr){
            if(i<0)
            count++;
        }
        return count;
    }
}


一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下
城并通过对抗恶魔来拯救公主。骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。

有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），
要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。

为了尽快到达公主，骑士决定每次只向右或向下移动一步。

 

编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/dungeon-game
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m=dungeon.length;
        int n=dungeon[0].length;
        int[][] dp=new int[m][n];
        dp[m-1][n-1]=Math.max(1,1-dungeon[m-1][n-1]);

        for(int i=m-2;i>=0;i--){
            dp[i][n-1]=Math.max(1,dp[i+1][n-1]-dungeon[i][n-1]);
        }

        for(int i=n-2;i>=0;i--){
            dp[m-1][i]=Math.max(1,dp[m-1][i+1]-dungeon[m-1][i]);
        }

        for(int i=m-2;i>=0;i--){
            for(int j=n-2;j>=0;j--){
                dp[i][j]=Math.max(1,Math.min(dp[i+1][j],dp[i][j+1])-dungeon[i][j]);
            }
        }
        return dp[0][0];
    }
}