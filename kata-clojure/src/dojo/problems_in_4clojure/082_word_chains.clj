(ns dojo.problems-in-4clojure.082-word-chains)
;; https://4clojure.oxal.org/#/problem/82

(defn sol
  [words]
  )

(= true (sol #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"}))
(= false (sol #{"cot" "hot" "bat" "fat"}))
(= false (sol #{"to" "top" "stop" "tops" "toss"}))
(= true (sol #{"spout" "do" "pot" "pout" "spot" "dot"}))
(= true (sol #{"share" "hares" "shares" "hare" "are"}))
(= false (sol #{"share" "hares" "hare" "are"}))
