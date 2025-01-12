package efub.assignment.community.post.domain;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.heart.domain.PostHeart;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.global.entity.BaseTimeEntity;
import efub.assignment.community.post.dto.PostModifyRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Boolean isAnonymous;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostHeart> postHeartList = new ArrayList<>();

    @Builder
    public Post(Long postId, String title, String content, Boolean isAnonymous, Member writer, Board board) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.isAnonymous = isAnonymous;
        this.writer = writer;
        this.board = board;
    }

    public void updatePost(PostModifyRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
