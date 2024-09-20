// Import the functions you need from the SDKs you need
import { initializeApp } from 'firebase/app'
import { getFirestore } from 'firebase/firestore'
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: 'AIzaSyDVQRACbaVktDHmfvDDKzLs2iSrbAqdqn4',
  authDomain: 'app-varsel.firebaseapp.com',
  projectId: 'app-varsel',
  storageBucket: 'app-varsel.appspot.com',
  messagingSenderId: '401005954065',
  appId: '1:401005954065:web:f76189bbccd2a794b89db0'
}

// Initialize Firebase
const app = initializeApp(firebaseConfig)
const db = getFirestore(app)
export default db
