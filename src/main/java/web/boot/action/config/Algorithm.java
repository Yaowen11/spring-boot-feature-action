package web.boot.action.config;

/**
 * @author z
 */
public enum Algorithm {
    /**
     * sha 1
     */
    SHA1 {
        @Override
        public String toString() {
            return "SHA-1";
        }
    },
    /**
     * md5
     */
    MD5 {
        @Override
        public String toString() {
            return "MD5";
        }
    },
    /**
     * sha 256
     */
    SHA256 {
        @Override
        public String toString() {
            return "SHA-256";
        }
    }

}
