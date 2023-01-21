package com.skhu.practice.service;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UrlToTitleService {

    private static final String BASE_URL = "http://localhost:8088/";
    private static final int SPLIT_URL_START_NUMBER = 3;
    private static Map<Integer, String> nodeToTitle;
    private static Map<String, Node> startNode;

    private class Node {

        private final int nodeNumber;
        private final String name;
        private List<Node> child;

        public Node(int nodeNumber, String name) {
            this.nodeNumber = nodeNumber;
            this.name = name;
            child = new ArrayList<>();
        }

        public void addChild(Node node) {
            child.add(node);
        }

        public Node findChild(String name) {
            for (Node childNode : child) {
                if (childNode.name.equals(name)) {
                    return childNode;
                }
            }

            return this;
        }
    }

    public String getTitleByUrl(String url) {
        settingUrlMapping();
        return search(url.split("/"));
    }

    public String search(String[] url) {
        if (url.length <= SPLIT_URL_START_NUMBER) {
            throw new IllegalArgumentException();
        }

        int depth = SPLIT_URL_START_NUMBER;
        Node currentNode = startNode.get(url[depth++]);

        while (depth != url.length) {
            currentNode = currentNode.findChild(url[depth++]);
        }

        return nodeToTitle.get(currentNode.nodeNumber);
    }

    public void settingUrlMapping() { // mapper 가 동착하도록 nodeToTitle 이 null 이면
        if (nodeToTitle == null) {
            nodeToTitle = new HashMap<>() {{
                put(0, "앨범 보드");
                put(1, "앨범 상세");
                put(2, "앨범 랭크");
                put(3, "앨범 작성");
                put(4, "앨범 리뷰");
                put(5, "앨범 리뷰 쓰기");
                put(6, "앨범 리뷰 보기");
                put(8, "아티스트 정보"); // 7은 그냥 user
            }};


            Node album = new Node(0, "album");
            Node albumDetail = new Node(1, "detail");
            Node albumRate = new Node(2, "rate");
            Node albumWrite = new Node(3, "write");
            Node albumReview = new Node(4, "review");
            Node albumReviewWrite = new Node(5, "write");
            Node albumReviewDetail = new Node(6, "detail");
            Node user = new Node(7, "user");
            Node userArtist = new Node(8, "detail");

            album.addChild(albumDetail);
            album.addChild(albumRate);
            album.addChild(albumWrite);
            album.addChild(albumReview);
            albumReview.addChild(albumReviewWrite);
            albumReview.addChild(albumReviewDetail);
            user.addChild(userArtist);

            startNode = new HashMap<>(){{
                put("album", album);
                put("user", user);
            }};
        }
    }
}