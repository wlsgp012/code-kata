(ns dojo.problems-in-4clojure.071-rearranging-code-i)
;; https://4clojure.oxal.org/#/problem/71

(= (last (sort (rest (reverse [2 5 4 1 3 6]))))
   (-> [2 5 4 1 3 6] reverse rest sort last)
   5)

;; ohters
;; count
