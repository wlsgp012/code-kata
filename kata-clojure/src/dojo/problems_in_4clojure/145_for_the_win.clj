(ns dojo.problems-in-4clojure.145-for-the-win)
;; https://4clojure.oxal.org/#/problem/145

(def sol (take 10 (iterate #(+ 4 %) 1)))

(= sol (for [x (range 40)
            :when (= 1 (rem x 4))]
        x))

(= sol (for [x (iterate #(+ 4 %) 0)
            :let [z (inc x)]
            :while (< z 40)]
        z))

(= sol (for [[x y] (partition 2 (range 20))]
        (+ x y)))

;; others

(range 1 40, 4)
