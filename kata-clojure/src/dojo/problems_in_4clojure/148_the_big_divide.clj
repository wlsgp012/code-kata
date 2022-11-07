(ns dojo.problems-in-4clojure.148-the-big-divide)

(defn sol [x y z]
  (letfn [(divisible [a b] (zero? (rem a b)))]
    (reduce #(+ %1 (bigint %2))
            (bigint 0)
            (filter #(or (divisible % y) (divisible % z)) (rest (range x))))))

(comment
  (= 0 (sol 3 17 11))

  (= 23 (sol 10 3 5))

  (= 233168 (sol 1000 3 5))

  (= "2333333316666668" (str (sol 100000000 3 5)))

  (= "110389610389889610389610"
     (str (sol (* 10000 10000 10000) 7 11)))

  (= "1277732511922987429116"
     (str (sol (* 10000 10000 10000) 757 809)))

  (= "4530161696788274281"
     (str (sol (* 10000 10000 1000) 1597 3571))))

;; others
(fn [n a b]
  (let [calc #(let [n (quot (dec n) %)] (/ (* % n (inc n)) 2))]
    (- (+ (calc a) (calc b)) (calc (* a b)))))

(fn [n a b]
  (letfn
      [(sum [x]
         (let [y (quot (dec (bigint n)) x)]
           (* x (/ (* (inc y) y) 2))))]
    (- (+ (sum a) (sum b)) (sum (* a b)))))
