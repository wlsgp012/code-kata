(ns dojo.problems-in-4clojure.096-beauty-is-symmetry)
;; https://4clojure.oxal.org/#/problem/96

(defn sol [xs]
  (loop [l (rest xs)]
    (let [firsts (map first l)
          [a b] (split-at (/ (count firsts) 2) firsts)]
      (cond
        (not= a (reverse b)) false
        (empty? (filter some? firsts)) true
        :else (recur (reduce #(into %1 (rest %2)) [] l))))))

(= (sol '(:a (:b nil nil) (:b nil nil))) true)

(= (sol '(:a (:b nil nil) nil)) false)

(= (sol '(:a (:b nil nil) (:c nil nil))) false)

(= (sol [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
         [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil]])
   true)

(= (sol [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
         [2 [3 nil [4 [5 nil nil] [6 nil nil]]] nil]])
   false)

(= (sol [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
         [2 [3 nil [4 [6 nil nil] nil]] nil]])
   false)

;; others
(fn s
  ([[n a b]] (s a b))
  ([[n1 a1 b1] [n2 a2 b2]]
   (cond (and (nil? n1) (nil? n2)) true
         (or (nil? n1) (nil? n2)) false
         :else (and (= n1 n2) (s a1 b2) (s a2 b1)))))

(fn [[n l r]]
  (= l ((fn f [[n l r]]
          (if n [n (f r) (f l)]))
        r)))

(fn symm? [x]
  (= x ((fn mir [[v l r :as t]]
          (when t
            (conj [v] (mir r) (mir l)))) x)))
