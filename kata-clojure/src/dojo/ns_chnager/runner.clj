(ns dojo.ns-chnager.runner
  (:require [clojure.string :as s]
            [clojure.java.io :as io]))


;; (def targetPath "/Users/user/development/source/projects/code-kata/kata-clojure/src/dojo/problems_in_4clojure")

(defn separate-filename
  [file-name]
  (let [separator "."
        last-index (s/last-index-of file-name separator)]
    (if (nil? last-index)
      file-name
      [(subs file-name 0 last-index) separator (subs file-name (inc last-index) (count file-name))])))

;; read files in directory
(defn get-files
  [path]
  (filter (fn [x] (and (.isFile x) (= "clj" (last (separate-filename (.getName x))))))
          (file-seq (io/file path))))

;; file name change + number padding 001 002
(defn padding
  [num count]
  (format (str "%0" count "d")
          (if (int? num)
            num
            (Integer/parseInt num))))

(defn post->pre
  [name sep]
  (let [splited (s/split name (re-pattern sep))]
    (str (padding (last splited) 3) sep (s/join sep (drop-last splited)))))

(defn change-name
  ([fullname] (change-name fullname "."))
  ([fullname sep]
   (let [splited (s/split fullname (re-pattern (str "\\" sep)))
         name (s/join sep (drop-last splited))
         extension (last splited)]
     (str (post->pre name "_") sep extension))))

(defn rename-file
  [f]
  (let [name (.getName f)
        path (.getParent f)]
    (.renameTo f (io/file (str path "/" (change-name name))))))

;; ns change
(defn read-file
  [file]
  (s/split (slurp (.getAbsolutePath file)) (re-pattern (System/lineSeparator))))

(defn remove-par
  [line]
  (s/replace line #"[()]" ""))

(defn change-ns-name
  [nsline]
  (let [separated (separate-filename (remove-par nsline))
        last-ns (post->pre (last separated) "-")]
    (str "(" (apply str (drop-last separated)) last-ns ")")))

(defn write-modified-ns
  [file]
  (let [code-as-string (read-file file)
        changed (change-ns-name (first code-as-string))]
    (spit (.getAbsolutePath file) (s/join (System/lineSeparator) (conj (rest code-as-string) changed)))))

;; process
(defn doit
  [path]
  (let [files (get-files path)]
    (doseq [f files]
      (write-modified-ns f)
      (rename-file f))))
