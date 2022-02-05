(ns dojo.problems-in-4clojure.026-fibonacci-sequence)
;; https://4clojure.oxal.org/#/problem/26

(defn fibo [x]
  (if (>= 1 x)
    x
    (+ (fibo (dec (dec x))) (fibo (dec x)))))

(defn answer
  ([n]
   (answer n '()))
  ([n l]
   (if (>= 1 n)
     (conj l n)
     (recur (dec n) (conj l (fibo n))))))

(= (answer 3) '(1 1 2))
(= (answer 6) '(1 1 2 3 5 8))
(= (answer 8) '(1 1 2 3 5 8 13 21))


;; others

(fn fib [x]
  (loop [an [] a 1 b 1 i 0]
    (if (= x i)
      an
      (recur (conj an a) b (+ a b) (inc i)))))

#(take % (map first (iterate (fn [[a b]] [b (+ a b)]) [1 1])))