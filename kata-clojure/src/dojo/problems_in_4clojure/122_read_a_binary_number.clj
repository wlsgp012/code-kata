(ns dojo.problems-in-4clojure.122-read-a-binary-number)
;; https://4clojure.oxal.org/#/problem/122

(defn sol [n]
  (reduce + (map-indexed #(* (int (Math/pow 2 %1)) (Integer/parseInt %2)) (reverse (re-seq #"\d" n)))))


(= 0     (sol "0"))

(= 7     (sol "111"))

(= 8     (sol "1000"))

(= 9     (sol "1001"))

(= 255   (sol "11111111"))

(= 1365  (sol "10101010101"))

(= 65535 (sol "1111111111111111"))


;; others
#(->> %
      (str "2r")
      (read-string))

#(reduce (fn [a e]
           (+ a a ({\0 0 \1 1} e)))
         0
         %)
