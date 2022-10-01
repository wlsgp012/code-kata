(ns dojo.problems-in-4clojure.104-write-roman-numerals)

(defn sol [x]
  (when (< x 4000)
    (let [norm-pairs [[1000 "M"] [900 "CM"] [500 "D"] [400 "CD"]
                      [100 "C"] [90 "XC"] [50 "L"] [40 "XL"]
                      [10 "X"] [9 "IX"] [5 "V"] [4 "IV"] [1 "I"]]
          convert (fn  [num [norm & norms] result]
                    (if (seq norm)
                      (let [[divider roman] norm
                            remainder (rem num divider)
                            quotient (quot num divider)
                            romans (clojure.string/join (take quotient (repeat roman)))]
                        (recur remainder norms (str result romans)))
                      result))]
      (convert x norm-pairs ""))))

(= "I" (sol 1))
(= "II" (sol 2))
(= "XXX" (sol 30))
(= "IV" (sol 4))
(= "CXL" (sol 140))
(= "DCCCXXVII" (sol 827))
(= "MMMCMXCIX" (sol 3999))
(= "XLVIII" (sol 48))

;; others
(fn roman [arabic]
  (letfn [(recurse [r a] (str r (roman (- arabic a))))]
    (cond (>= arabic 1000) (recurse "M" 1000)
          (>= arabic 900) (recurse "CM" 900)
          (>= arabic 500) (recurse "D" 500)
          (>= arabic 100) (recurse "C" 100)
          (>= arabic 90) (recurse "XC" 90)
          (>= arabic 40) (recurse "XL" 40)
          (>= arabic 10) (recurse "X" 10)
          (>= arabic 9) (recurse "IX" 9)
          (>= arabic 5) (recurse "V" 5)
          (>= arabic 4) (recurse "IV" 4)
          (>= arabic 1) (recurse "I"  1))))

(fn __ [roman]
  (let [table
        {1000 "M" 900 "CM" 500 "D" 400 "CD"
    	 100 "C" 90 "XC" 50 "L" 40 "XL"
  	 10 "X" 9 "IX" 5 "V" 4 "IV" 1 "I" 0 ""}]
    (loop [cur roman final "" numerator 0]
      (if (= 0 cur)
        final
        (recur
         (- cur numerator)
         (str final (table numerator))
         (apply (partial max-key identity)
                (filter (partial >= (- cur numerator)) (keys table))))))))


(fn wnum [n]
  (let [r (sorted-map-by > 1000 "M" 900 "CM" 500 "D" 400 "CD" 100 "C"
                         90 "XC" 50 "L" 40 "XL" 10 "X" 9 "IX" 5 "V" 4 "IV" 1 "I")
        m (some #(when (>= (- n %) 0) %) (keys r))]
    (when-not (nil? m)
      (str (r m) (wnum (- n m))))))
