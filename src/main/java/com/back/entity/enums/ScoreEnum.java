package com.back.entity.enums;


/**
 * 점수 타입과 타입별 점수 수치를 기록하는 enum
 * 이걸 그냥 각 기능별 로직에서 불러써도 되긴 하는데
 * 그러면 여러 기능을 횡단하게 되어버린다. 책임이 분산됨...
 * 도메인 관점에서 생각해보면 점수 도메인을 따로 만드는게 낫다?
 * (이 규모에서는 일이 커지는데 중간 규모의 다른 접근법 있을지 고민 필요)
 * 라고 생각했는데 enum을 만들 필요까지는 없었다
 */
public enum ScoreEnum {
    POST_CREATE(3),
    COMMENT_CREATE(1);

    private final long score;

    public long getScore(){
        return score;
    }

    ScoreEnum (long score){
        this.score=score;
    }

}
