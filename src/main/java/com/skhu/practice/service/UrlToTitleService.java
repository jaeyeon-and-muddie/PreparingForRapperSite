package com.skhu.practice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UrlToTitleService {

    private static String BASE_URL = "http://localhost:8088/";
    private static int SPLIT_URL_START_NUMBER = 3;
    private static Map<Integer, String> nodeToTitle;
    private static Map<String, Node> startNode;

    private class Node {

        private int nodeNumber;
        private String name;
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
        String[] tempSplitUrl = url.split("/");
        String[] resultSplitUrl = new String[tempSplitUrl.length - SPLIT_URL_START_NUMBER];

        for (int index = SPLIT_URL_START_NUMBER; index < tempSplitUrl.length; index++) {
            resultSplitUrl[index - SPLIT_URL_START_NUMBER] = tempSplitUrl[index];
        }

        return search(resultSplitUrl);
    }

    public String search(String[] url) {
        // 이제 여기서부터 순서대로 진행하면 됨, startNode 찾고, 그 다음에 url path 찾아서 가면 되는데.. 만일 없는 path 가 나오면
        int depth = 0;
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
                put(8, "아티스트 정보"); // 6은 그냥 user
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
