(ns dojo.problems-in-4clojure.157-indexing-sequences)

(def sol #(vec (zipmap % (range))))

(def sol2
  (fn [v]
    (map-indexed (fn [ix e] [e ix]) v)))

(def sol3
  (fn [v]
    (map reverse (map-indexed vector v))))

(= (sol [:a :b :c]) [[:a 0] [:b 1] [:c 2]])

(= (sol [0 1 3]) '((0 0) (1 1) (3 2)))

(= (sol [[:foo] {:bar :baz}]) [[[:foo] 0] [{:bar :baz} 1]])

;; others
(fn [n] (map vector n (range)))

(fn[s] (partition 2 (interleave s (range))))

#(vec (zipmap % (range)))

#(map vector % (range))
