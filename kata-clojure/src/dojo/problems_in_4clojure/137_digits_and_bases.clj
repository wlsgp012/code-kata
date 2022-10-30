(ns dojo.problems-in-4clojure.137-digits-and-bases)

(defn sol [n base]
  (loop [nn n r '()]
    (cond
      (zero? n) [0]
      (zero? nn) r
      :else (recur (quot nn base) (cons (mod nn base) r)))))

(= [1 2 3 4 5 0 1] (sol 1234501 10))

(= [0] (sol 0 11))

(= [1 0 0 1] (sol 9 2))

(= [1 0] (let [n (rand-int 100000)] (sol n n)))

(= [22 6 10 5 0 19 6 9 6 31] (sol (bigint (- (Math/pow 2 53) 1)) 42))

;; others
(fn get-digits [x base]
  (if (> base x) [x]
      (let [[q r] ((juxt quot mod) x base)]
        (conj (get-digits q base) r))))
