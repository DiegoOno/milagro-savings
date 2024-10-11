import { Module } from '@nestjs/common';
import { AuthService } from './auth.service';
import { UserModule } from 'src/user/user.module';
import { SecurityModule } from 'src/security/security.module';
import { AuthController } from './auth.controller';

@Module({
  imports: [UserModule, SecurityModule],
  providers: [AuthService],
  exports: [AuthService],
  controllers: [AuthController],
})
export class AuthModule {}
