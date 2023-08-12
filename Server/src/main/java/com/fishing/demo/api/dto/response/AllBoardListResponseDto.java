//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.fishing.demo.api.dto.response;

public class AllBoardListResponseDto {
    private Long BoardId;
    private String title;
    private String mainImageUrl;
    private double averageScore;
    private int likeCount;

    public static AllBoardListResponseDto of(Long BoardId, String title, String mainImageUrl, double averageScore, int likeCount) {
        return builder().BoardId(BoardId).title(title).mainImageUrl(mainImageUrl).averageScore(averageScore).likeCount(likeCount).build();
    }

    public static AllBoardListResponseDtoBuilder builder() {
        return new AllBoardListResponseDtoBuilder();
    }

    public Long getBoardId() {
        return this.BoardId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getMainImageUrl() {
        return this.mainImageUrl;
    }

    public double getAverageScore() {
        return this.averageScore;
    }

    public int getLikeCount() {
        return this.likeCount;
    }

    public AllBoardListResponseDto() {
    }

    public AllBoardListResponseDto(Long BoardId, String title, String mainImageUrl, double averageScore, int likeCount) {
        this.BoardId = BoardId;
        this.title = title;
        this.mainImageUrl = mainImageUrl;
        this.averageScore = averageScore;
        this.likeCount = likeCount;
    }

    public static class AllBoardListResponseDtoBuilder {
        private Long BoardId;
        private String title;
        private String mainImageUrl;
        private double averageScore;
        private int likeCount;

        AllBoardListResponseDtoBuilder() {
        }

        public AllBoardListResponseDtoBuilder BoardId(Long BoardId) {
            this.BoardId = BoardId;
            return this;
        }

        public AllBoardListResponseDtoBuilder title(String title) {
            this.title = title;
            return this;
        }

        public AllBoardListResponseDtoBuilder mainImageUrl(String mainImageUrl) {
            this.mainImageUrl = mainImageUrl;
            return this;
        }

        public AllBoardListResponseDtoBuilder averageScore(double averageScore) {
            this.averageScore = averageScore;
            return this;
        }

        public AllBoardListResponseDtoBuilder likeCount(int likeCount) {
            this.likeCount = likeCount;
            return this;
        }

        public AllBoardListResponseDto build() {
            return new AllBoardListResponseDto(this.BoardId, this.title, this.mainImageUrl, this.averageScore, this.likeCount);
        }

        public String toString() {
            return "AllBoardListResponseDto.AllBoardListResponseDtoBuilder(BoardId=" + this.BoardId + ", title=" + this.title + ", mainImageUrl=" + this.mainImageUrl + ", averageScore=" + this.averageScore + ", likeCount=" + this.likeCount + ")";
        }
    }
}
