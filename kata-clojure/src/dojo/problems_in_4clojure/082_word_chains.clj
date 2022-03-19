(ns dojo.problems-in-4clojure.082-word-chains)
;; https://4clojure.oxal.org/#/problem/82

;; wrong answer
(defn sol
  [words]
  (letfn [(intersection [a b] (clojure.set/intersection (set a) (set b)))
          (difference [a b] (clojure.set/difference (set a) (set b)))
          (a' [a b] (difference a (intersection a b)))
          (abscount [a b] (Math/abs (count (a' a b))))
          (linked [a b] (= 1 (abscount a b) (abscount b a)))]
    (every? #(= 1 (count (filter (fn [x] (linked % x)) words))) words)))

(= true (sol #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"}))
(= false (sol #{"cot" "hot" "bat" "fat"}))
(= false (sol #{"to" "top" "stop" "tops" "toss"}))
(= true (sol #{"spout" "do" "pot" "pout" "spot" "dot"}))
(= true (sol #{"share" "hares" "shares" "hare" "are"}))
(= false (sol #{"share" "hares" "hare" "are"}))
