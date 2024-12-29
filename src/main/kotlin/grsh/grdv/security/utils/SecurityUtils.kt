package grsh.grdv.security.utils

import org.mindrot.jbcrypt.BCrypt

object SecurityUtils {

    fun encodePassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    fun verify(password: String, hashPassword: String): Boolean {
        return BCrypt.checkpw(password, hashPassword)
    }
}