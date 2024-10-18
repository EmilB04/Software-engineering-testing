import { getAuth, onAuthStateChanged } from 'firebase/auth'

const blockUnAuth = async (to: any, from: any, next: any) => {
  const getCurrentUser = () => {
    return new Promise(resolve => {
      onAuthStateChanged(getAuth(), (user) => {
        resolve(user)
      }, (error) => {
        console.error(error)
        resolve(null)
      })
    })
  }
  const user = await getCurrentUser()
  if (user) {
    return next()
  }
  next('/auth/welcome')
}
const blockAuth = async (to: any, from: any, next: any) => {
  const getCurrentUser = () => {
    return new Promise(resolve => {
      onAuthStateChanged(getAuth(), (user) => {
        resolve(user)
      }, (error) => {
        console.error(error)
        resolve(null)
      })
    })
  }
  const user = await getCurrentUser()
  if (!user) {
    return next()
  }
  next('/dashboard')
}

export {
  blockUnAuth,
  blockAuth,
}
