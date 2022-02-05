(ns dojo.problems-in-4clojure.022-count-a-sequence)
;; https://4clojure.oxal.org/#/problem/22

(defn answer
  [l]
  (reduce (fn [sum _] (inc sum))
          0
          l))

(= (answer '(1 2 3 3 1)) 5)
(= (answer "Hello World") 11)
(= (answer [[1 2] [3 4] [5 6]]) 3)
(= (answer '(13)) 1)
(= (answer '(:a :b :c)) 3)

;; other

(comp inc first last (partial map-indexed list))

#(reduce + (map (constantly 1) %))

#(-> % vec .length)